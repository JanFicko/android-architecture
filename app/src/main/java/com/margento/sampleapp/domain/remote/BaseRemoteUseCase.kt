package com.margento.sampleapp.domain.remote

import com.margento.sampleapp.data.remote.RemoteRepositoryContract

abstract class BaseRemoteUseCase(protected val remoteRepository: RemoteRepositoryContract)