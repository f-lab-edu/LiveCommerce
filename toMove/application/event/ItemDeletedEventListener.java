package com.flab.livecommerce.application.event;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.domain.item.event.ItemDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ItemDeletedEventListener {

    private final DeleteImageProcessor deleteImageProcessor;

    public ItemDeletedEventListener(DeleteImageProcessor deleteImageProcessor) {
        this.deleteImageProcessor = deleteImageProcessor;
    }

    @Async
    @EventListener
    public void deleteImages(ItemDeletedEvent event) {
        deleteImageProcessor.execute(event.getItemId());
    }
}
