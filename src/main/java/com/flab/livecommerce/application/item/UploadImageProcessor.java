package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemImage;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.domain.item.ItemRepository;
import java.io.IOException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageProcessor {

    private final ItemImageRepository itemImageRepository;
    private final ItemRepository itemRepository;
    private final ImageUploader imageUploader;

    public UploadImageProcessor(
        ItemImageRepository itemImageRepository,
        ItemRepository itemRepository,
        ImageUploader imageUploader
    ) {
        this.itemImageRepository = itemImageRepository;
        this.itemRepository = itemRepository;
        this.imageUploader = imageUploader;
    }

    @Transactional
    public void execute(Long itemId, MultipartFile thumbnailImage, MultipartFile[] specificImages)
        throws IOException {

        Item item = itemRepository.findById(itemId);

        ItemImage storedThumbnail = imageUploader.uploadImage(thumbnailImage);
        storedThumbnail.addItem(item);
        itemImageRepository.save(storedThumbnail);

        for (MultipartFile specificImage : specificImages) {
            ItemImage storedSpecific = imageUploader.uploadImage(specificImage);
            storedSpecific.addItem(item);
            itemImageRepository.save(storedSpecific);
        }
    }
}
