package ru.otus.archiveservice.scheduled;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.archiveservice.dto.AstronomyResponse;
import ru.otus.archiveservice.dto.RealTimeResponse;
import ru.otus.archiveservice.service.ArchiveService;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
@Service
public class ArchiveJob {

    final ArchiveService archiveService;
    final WebClient webClient;

    private String ipAddress;

    @Scheduled(fixedDelay = 300000)
    public void runGetWeatherPointJob() {
        log.info("Start job to getting weather point");
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/real/{ip}")
                        .build(ipAddress))
                .retrieve()
                .bodyToMono(RealTimeResponse.class)
                .flatMap(Mono::just)
                .subscribe(archiveService::addWeatherPointToArchive);
        log.info("Finish job to getting weather point");
    }

//    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "0 03 21  * * ?")
    public void runGetAstronomyPointJob() {
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/astronomy/{ip}")
                        .build(ipAddress))
                .retrieve()
                .bodyToMono(AstronomyResponse.class)
                .flatMap(Mono::just)
                .subscribe(archiveService::addAstronomyPointToArchive);
    }

    @EventListener
    private void onApplicationEvent(ContextRefreshedEvent event) {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            ipAddress = "212.202.11.111";
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
