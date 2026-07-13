package com.example.demo.infrastructure.messaging;

import com.example.demo.domain.event.SubscriptionCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionEventListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleSubscriptionCreated(SubscriptionCreatedEvent event){
        System.out.println("\n========================================================");
        System.out.println("[RabbitMQ Listener] ¡Mensaje recibido de la cola!");
        System.out.println("PROCESANDO FACTURACIÓN ASÍNCRONA:");
        System.out.println(" -> ID Suscripción: " + event.subscriptionId());
        System.out.println(" -> ID Usuario: " + event.userId());
        System.out.println(" -> ID Plan: " + event.planId());
        System.out.println("Generando PDF de factura y simulando envío de email...");

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("[Facturación] ¡Factura procesada con éxito en segundo plano!");
        System.out.println("========================================================\n");
    }
}
