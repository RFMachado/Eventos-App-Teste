package br.com.teste.sicredi.feature

import br.com.teste.sicredi.feature.detail.domain.entity.EventDetailData
import br.com.teste.sicredi.feature.events.domain.entity.EventData

object StubFactory {

    const val EVENT_ID = 1

    fun stubEvent() = EventData(
        id = 1,
        title = "Feira de adoção de animais na Redenção",
        description = "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!",
        dateString = "02/08/2020 , 14:00 h",
        image = "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png"
    )

    fun stubEventDetail() = EventDetailData(
        id = 1,
        title = "Feira de adoção de animais na Redenção",
        description = "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!",
        dateString = "02/08/2020 , 14:00 h",
        image = "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        price = "2.99",
        lng = -51.2146267,
        lat = -30.0392981
    )

    fun stubEventsList() = listOf<EventData>(stubEvent(), stubEvent(), stubEvent())
}