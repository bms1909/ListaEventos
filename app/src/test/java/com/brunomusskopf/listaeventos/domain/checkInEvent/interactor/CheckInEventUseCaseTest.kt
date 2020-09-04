package com.brunomusskopf.listaeventos.domain.checkInEvent.interactor

import com.brunomusskopf.listaeventos.domain.base.StringValidationStatus
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventValidation
import com.brunomusskopf.listaeventos.inject.CheckInEventKoinModule
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

class CheckInEventUseCaseTest : KoinTest {

    @get:Rule
    val koinRule = KoinTestRule.create {
        modules(CheckInEventKoinModule.getModuleCheckInEventDomain())
    }

    private val repository = CheckInEventRepositoryMock()

    private val useCase = CheckInEventUseCase(repository)

    @Test
    fun `testValidateFields Valid`() {
        val result = testValidateFields(1, "Nome", "email@exemplo.com")
        assert(result == null)
    }

    @Test
    fun `testValidateFields Invalid Email`() {
        val result = testValidateFields(2, "Nome", "email@exemplo")
        assert(result?.nameStatus == null)
        assert(result?.emailStatus == StringValidationStatus.INVALID)
    }

    @Test
    fun `testValidateFields Empty`() {
        val result = testValidateFields(3, "", "")
        assert(result?.nameStatus == StringValidationStatus.EMPTY)
        assert(result?.emailStatus == StringValidationStatus.EMPTY)
        assert(result?.errorMessage.isNullOrEmpty())
    }

    @Test
    fun `testValidateFields no ID`() {
        val request = CheckInEventRequest()

        request.name = "Nome"
        request.email = "email@exemplo.com"

        val validation = useCase.validateFields(request)

        assert(validation != null)
        assert(validation?.errorMessage?.isNotBlank() == true)
    }


    private fun testValidateFields(eventId : Int, name : String, email : String) : CheckInEventValidation? {
        val request = CheckInEventRequest()
        request.eventId = eventId
        request.name = name
        request.email = email

        return useCase.validateFields(request)
    }

    @Test
    fun `testSendCheckInOrError error`() {
        val request = CheckInEventRequest()
        val result = testSendCheckInOrError(false, request)
        assert(result != null)
    }

    @Test
    fun `testSendCheckInOrError success`() {
        val request = CheckInEventRequest()
        val result = testSendCheckInOrError(true, request)
        assert(result == null)
    }

    private fun testSendCheckInOrError(mockSuccess : Boolean, request : CheckInEventRequest) = runBlocking {
        repository.mockSuccess = mockSuccess
        useCase.sendCheckInOrError(request)
    }
}