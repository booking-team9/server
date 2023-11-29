package com.komsije.booking.mapper;

import com.komsije.booking.dto.NotificationDto;
import com.komsije.booking.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserDtoMapper.class})
public abstract class NotificationMapper {
    public abstract NotificationDto toDto(Notification notification);
    public abstract Notification fromDto(NotificationDto notificationDto);
    public abstract List<NotificationDto> toDto(List<Notification> notificationList);
    public abstract void update(@MappingTarget Notification notification, NotificationDto notificationDto);
}
