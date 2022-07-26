package com.antoniocmoura.cloud.parking.infrastructure.api;

import com.antoniocmoura.cloud.parking.application.parking.checkout.CheckOutParkingCommand;
import com.antoniocmoura.cloud.parking.application.parking.checkout.CheckOutParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.create.CreateParkingCommand;
import com.antoniocmoura.cloud.parking.application.parking.create.CreateParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.delete.DeleteParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.find.get.GetParkingByIdUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.find.list.ListParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.update.UpdateParkingCommand;
import com.antoniocmoura.cloud.parking.application.parking.update.UpdateParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.domain.pagination.Pagination;
import com.antoniocmoura.cloud.parking.domain.pagination.SearchQuery;
import com.antoniocmoura.cloud.parking.infrastructure.parking.model.CreateParkingRequest;
import com.antoniocmoura.cloud.parking.infrastructure.parking.model.ParkingResponse;
import com.antoniocmoura.cloud.parking.infrastructure.parking.model.UpdateParkingRequest;
import com.antoniocmoura.cloud.parking.infrastructure.parking.presenter.ParkingPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ParkingController implements ParkingEndpoint {

    private final CreateParkingUseCaseContract createParkingUseCase;
    private final GetParkingByIdUseCaseContract getParkingByIdUseCase;
    private final ListParkingUseCaseContract listParkingUseCase;
    private final DeleteParkingUseCaseContract deleteParkingUseCase;
    private final UpdateParkingUseCaseContract updateParkingUseCase;
    private final CheckOutParkingUseCaseContract checkOutParkingUseCase;

    public ParkingController(
            final CreateParkingUseCaseContract createParkingUseCase,
            final GetParkingByIdUseCaseContract getParkingByIdUseCase,
            final ListParkingUseCaseContract listParkingUseCase,
            final DeleteParkingUseCaseContract deleteParkingUseCase,
            final UpdateParkingUseCaseContract updateParkingUseCase,
            final CheckOutParkingUseCaseContract checkOutParkingUseCase) {
        this.createParkingUseCase = createParkingUseCase;
        this.getParkingByIdUseCase = getParkingByIdUseCase;
        this.listParkingUseCase = listParkingUseCase;
        this.deleteParkingUseCase = deleteParkingUseCase;
        this.updateParkingUseCase = updateParkingUseCase;
        this.checkOutParkingUseCase = checkOutParkingUseCase;
    }

    @Override
    public ResponseEntity<?> create(final CreateParkingRequest input) {
        final var aCommand = CreateParkingCommand.with(
                input.license(),
                input.state(),
                input.model(),
                input.color()
        );
        final var result = ParkingPresenter.present(this.createParkingUseCase.execute(aCommand));
        return ResponseEntity.created(URI.create("/parking/" + result.id())).body(result);
    }

    @Override
    public void deleteById(String id) {
        this.deleteParkingUseCase.execute(id);
    }

    @Override
    public ResponseEntity<?> updateById(UpdateParkingRequest input) {
        final var aCommand = UpdateParkingCommand.with(
                input.id(),
                input.license(),
                input.state(),
                input.model(),
                input.color()
        );
        final var result = updateParkingUseCase.execute(aCommand);
        return ResponseEntity.ok(ParkingPresenter.present(result));
    }

    @Override
    public ParkingResponse getById(String id) {
        return ParkingPresenter.present(this.getParkingByIdUseCase.execute(id));
    }

    @Override
    public Pagination<ParkingResponse> findAll(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction) {
        return this.listParkingUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(ParkingPresenter::present);
    }

    @Override
    public ResponseEntity<?> checkOut(String id) {
        final var aCommand = CheckOutParkingCommand.with(id);
        final var result = checkOutParkingUseCase.execute(aCommand);
        return ResponseEntity.ok(ParkingPresenter.present(result));
    }
}
