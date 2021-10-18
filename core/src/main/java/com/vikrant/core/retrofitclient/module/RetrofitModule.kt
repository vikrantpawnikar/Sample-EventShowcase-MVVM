package com.vikrant.core.retrofitclient.module

import com.vikrant.core.retrofitclient.ServiceClient.apiInterface
import org.koin.dsl.module

val retrofit_module = module {
    single { apiInterface }
}