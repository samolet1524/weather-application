package ru.otus.archiveservice.scheduled;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
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
public class ArchiveJob {

    final ArchiveService archiveService;
    final WebClient webClient;

    private String ipAddress;

    @Scheduled(fixedDelay = 300000)
    public void runGetWeatherPointJob() {
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/real/{ip}")
                        .build(ipAddress))
                .retrieve()
                .bodyToMono(RealTimeResponse.class)
                .flatMap(Mono::just)
                .subscribe(archiveService::addWeatherPointToArchive);
    }

    @Scheduled(cron = "0 0 1 * * ?")
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
            ipAddress = datagramSocket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
