package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        try {
            log.info("createBillingAccount request received {}", billingRequest);

            log.info("patientId: {}, name: {}, email: {}",
                    billingRequest.getPatientId(),
                    billingRequest.getName(),
                    billingRequest.getEmail());

            BillingResponse response = BillingResponse.newBuilder()
                    .setAccountId("12345")
                    .setStatus("ACTIVE")
                    .build();

            log.info("Sending response: {}", response);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            log.error("Error in createBillingAccount", e);
            responseObserver.onError(e);
        }
    }

}
