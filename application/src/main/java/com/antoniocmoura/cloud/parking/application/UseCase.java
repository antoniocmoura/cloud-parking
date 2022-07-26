package com.antoniocmoura.cloud.parking.application;

/* Patern command */

public abstract class UseCase<IN, OUT> {

   public abstract OUT execute(IN anIn);

}
