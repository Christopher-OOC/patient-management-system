package org.javalord.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class.getName());

    @Override
    public void createBillingAccount(
            billing.BillingRequest billingRequest,
            io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver
            ) {
        log.info("createBillingAccount request responseObserver {}", billingRequest.toString());

        // Business logic
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
