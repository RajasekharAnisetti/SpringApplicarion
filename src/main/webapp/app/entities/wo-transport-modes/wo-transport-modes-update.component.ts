import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoTransportModes, WoTransportModes } from 'app/shared/model/wo-transport-modes.model';
import { WoTransportModesService } from './wo-transport-modes.service';
import { ITransportModes } from 'app/shared/model/transport-modes.model';
import { TransportModesService } from 'app/entities/transport-modes';
import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';
import { WoCustomsBrokerageService } from 'app/entities/wo-customs-brokerage';

@Component({
    selector: 'jhi-wo-transport-modes-update',
    templateUrl: './wo-transport-modes-update.component.html'
})
export class WoTransportModesUpdateComponent implements OnInit {
    woTransportModes: IWoTransportModes;
    isSaving: boolean;

    transportmodes: ITransportModes[];

    wocustomsbrokerages: IWoCustomsBrokerage[];

    editForm = this.fb.group({
        id: [],
        transportModesId: [],
        woCustomsBrokerageId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected woTransportModesService: WoTransportModesService,
        protected transportModesService: TransportModesService,
        protected woCustomsBrokerageService: WoCustomsBrokerageService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ woTransportModes }) => {
            this.updateForm(woTransportModes);
            this.woTransportModes = woTransportModes;
        });
        this.transportModesService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITransportModes[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITransportModes[]>) => response.body)
            )
            .subscribe((res: ITransportModes[]) => (this.transportmodes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.woCustomsBrokerageService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IWoCustomsBrokerage[]>) => mayBeOk.ok),
                map((response: HttpResponse<IWoCustomsBrokerage[]>) => response.body)
            )
            .subscribe(
                (res: IWoCustomsBrokerage[]) => (this.wocustomsbrokerages = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    updateForm(woTransportModes: IWoTransportModes) {
        this.editForm.patchValue({
            id: woTransportModes.id,
            transportModesId: woTransportModes.transportModesId,
            woCustomsBrokerageId: woTransportModes.woCustomsBrokerageId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const woTransportModes = this.createFromForm();
        if (woTransportModes.id !== undefined) {
            this.subscribeToSaveResponse(this.woTransportModesService.update(woTransportModes));
        } else {
            this.subscribeToSaveResponse(this.woTransportModesService.create(woTransportModes));
        }
    }

    private createFromForm(): IWoTransportModes {
        const entity = {
            ...new WoTransportModes(),
            id: this.editForm.get(['id']).value,
            transportModesId: this.editForm.get(['transportModesId']).value,
            woCustomsBrokerageId: this.editForm.get(['woCustomsBrokerageId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoTransportModes>>) {
        result.subscribe((res: HttpResponse<IWoTransportModes>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTransportModesById(index: number, item: ITransportModes) {
        return item.id;
    }

    trackWoCustomsBrokerageById(index: number, item: IWoCustomsBrokerage) {
        return item.id;
    }
}
