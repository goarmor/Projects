package com.company.superandyeyev.vkgroupreader.common.utils;

import com.company.superandyeyev.vkgroupreader.model.Owner;
import com.company.superandyeyev.vkgroupreader.model.WallItem;
import com.company.superandyeyev.vkgroupreader.rest.model.response.ItemsWithSendersResponse;

import java.util.List;

/**
 * Created by DIMON on 02.10.2017.
 */

public class VkListHelper {

    //заполняет поля SenderName и SenderPhoto для отправителя записи или репоста
    public static List<WallItem> getWallList(ItemsWithSendersResponse<WallItem> response) {
        List<WallItem> wallItems = response.items;

        for (WallItem wallItem : wallItems) {
            Owner sender = response.getSender(wallItem.getFromId());
            wallItem.setSenderName(sender.getFullName());
            wallItem.setSenderPhoto(sender.getPhoto());

            wallItem.setAttachmentsString(Utils
                    .convertAttachmentsToFontIcons(wallItem.getApiAttachments()));

            if (wallItem.haveSharedRepost()) {
                Owner repostSender = response.getSender(wallItem.getSharedRepost().getFromId());
                wallItem.getSharedRepost().setSenderName(repostSender.getFullName());
                wallItem.getSharedRepost().setSenderPhoto(repostSender.getPhoto());

                wallItem.getSharedRepost().setAttachmentsString(Utils
                        .convertAttachmentsToFontIcons(wallItem.getSharedRepost().getApiAttachments()));
            }
        }
        return wallItems;
    }
}
