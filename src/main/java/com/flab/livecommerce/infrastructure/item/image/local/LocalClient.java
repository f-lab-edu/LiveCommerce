package com.flab.livecommerce.infrastructure.item.image.local;

import com.flab.livecommerce.domain.image.FileClient;
import com.flab.livecommerce.domain.item.Item;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

public class LocalClient implements FileClient {

    // C:\Temp\
    private String basePath;
    @Value("${server.port}")
    private String localServerPort;

    private String localServerIp;

    public LocalClient(String basePath, String localServerPort, String localServerIp) {
        this.basePath = basePath;
        this.localServerPort = localServerPort;
        this.localServerIp = localServerIp;
    }

    public LocalClient() {
    }

    @Override
    public String getUploadPath(String imageDbPath) {
        return basePath + imageDbPath;
    }

    @Override
    public List<String> loadAll(Item item) {
        return item.getItemImages().stream().map(
            itemImage -> localServerIp + localServerPort + getUploadPath(
                itemImage.getUrl())
        ).collect(Collectors.toList());
    }

}
