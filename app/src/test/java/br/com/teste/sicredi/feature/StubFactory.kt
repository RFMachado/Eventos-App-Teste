package br.com.teste.sicredi.feature

import br.com.teste.sicredi.feature.events.domain.entity.EventData

object StubFactory {

    fun stubEvent() = EventData(
        id = 1,
        title = "Feira de adoção de animais na Redenção",
        description = "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!",
        dateString = "02/08/2020 , 14:00 h",
        image = "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png"
    )

    fun stubEventsList() = listOf<EventData>(stubEvent(), stubEvent(), stubEvent())
}