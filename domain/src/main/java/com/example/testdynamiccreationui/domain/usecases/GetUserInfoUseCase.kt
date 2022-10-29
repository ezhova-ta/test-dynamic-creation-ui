package com.example.testdynamiccreationui.domain.usecases

import com.example.testdynamiccreationui.domain.exceptions.GettingUserInfoException
import com.example.testdynamiccreationui.domain.models.user.User
import com.example.testdynamiccreationui.domain.repositories.Repository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
	private val repository: Repository
) {
	@Throws(GettingUserInfoException::class)
	suspend operator fun invoke(action: String, params: Map<String, String>): User {
		return repository.getUserInfo(action, params)
	}
}