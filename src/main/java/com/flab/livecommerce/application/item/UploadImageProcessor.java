package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.image.FileStorageService;
import com.flab.livecommerce.domain.image.ItemImage;
import com.flab.livecommerce.domain.image.ItemImageRepository;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemRepository;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class UploadImageProcessor {

    private final ItemImageRepository itemImageRepository;
    private final ItemRepository itemRepository;
    private final FileStorageService fileStorageService;
    private final String uriPrefix;


    public UploadImageProcessor(
        ItemImageRepository itemImageRepository,
        ItemRepository itemRepository,
        FileStorageService fileStorageService,
        String uriPrefix
    ) {
        this.itemImageRepository = itemImageRepository;
        this.itemRepository = itemRepository;
        this.fileStorageService = fileStorageService;
        this.uriPrefix = uriPrefix;
    }

    @Transactional
    public List<String> execute(Long itemId, MultipartFile thumbnailImage,
        MultipartFile[] specificImages)
        throws IOException {

        Item item = itemRepository.findById(itemId);
        log.info(">>>> item 조회 결과 ={}", item);

        ItemImage storedThumbnail = fileStorageService.uploadImage(thumbnailImage);

        storedThumbnail.addItem(item);
        itemImageRepository.save(storedThumbnail);

        for (MultipartFile specificImage : specificImages) {
            ItemImage storedSpecific = fileStorageService.uploadImage(specificImage);
            storedSpecific.addItem(item);
            itemImageRepository.save(storedSpecific);
        }
        log.info("uriprefix={}", uriPrefix);
        return item.findItemImageUris(uriPrefix);
    }
}
