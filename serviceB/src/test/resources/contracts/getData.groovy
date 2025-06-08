package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Когда GET /api/hello вызван, сервис B возвращает сообщение с приветствием"
    request {
        method 'GET'
        url '/api/hello'
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body(
                message: "Hello from B"
        )
    }
}