package br.com.teste.sicredi.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.teste.sicredi.feature.api.ServerApi
import br.com.teste.sicredi.feature.detail.repository.EventDetailRepository
import br.com.teste.sicredi.feature.events.repository.EventsRepository
import br.com.teste.sicredi.feature.util.FilesFromTestResources
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class EventDetailRepositoryTest {
    private lateinit var server: MockWebServer
    private lateinit var repository: EventDetailRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun beforeEachTest() {
        server = MockWebServer()

        val serverApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(server.url("/").toString())
            .build()
            .create(ServerApi::class.java)

        repository = EventDetailRepository(
            serverApi = serverApi,
            scheduler =  Schedulers.trampoline()
        )
    }

    @After
    fun afterEachTest() {
        server.shutdown()
    }

    @Test
    fun `check valid userdata`() {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(FilesFromTestResources.getJson("detail/eventDetailData"))
        )

        repository.fetchEventDetail(1)
            .test()
            .assertNoErrors()
            .assertComplete()
    }
}