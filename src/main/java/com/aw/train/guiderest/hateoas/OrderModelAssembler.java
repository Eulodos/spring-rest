package com.aw.train.guiderest.hateoas;

import com.aw.train.guiderest.entity.Order;
import com.aw.train.guiderest.entity.Status;
import com.aw.train.guiderest.web.OrderController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {
        final EntityModel<Order> orderEntityModel = EntityModel.of(order,
                linkTo(methodOn(OrderController.class).getOne(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getAll()).withRel("orders"));

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderEntityModel.add(
                    linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"),
                    linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete")
            );
        }

        return orderEntityModel;
    }
}
