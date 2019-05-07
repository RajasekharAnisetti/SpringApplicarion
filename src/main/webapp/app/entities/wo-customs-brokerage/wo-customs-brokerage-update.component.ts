import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoCustomsBrokerage, WoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';
import { WoCustomsBrokerageService } from './wo-customs-brokerage.service';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from 'app/entities/wo-work-order';

@Component({
    selector: 'jhi-wo-customs-brokerage-update',
    templateUrl: './wo-customs-brokerage-update.component.html'
})
export class WoCustomsBrokerageUpdateComponent implements OnInit {
    woCustomsBrokerage: IWoCustomsBrokerage;
    isSaving: boolean;

    woworkorders: IWoWorkOrder[];

    editForm = this.fb.group({
        id: [],
        monthlyImportShipment: [null, [Validators.maxLength(255)]],
        monthlyExportShipment: [null, [Validators.maxLength(255)]],
        shipmentValue: [null, [Validators.maxLength(255)]],
        productInfo: [null, [Validators.maxLength(255)]]
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected woCustomsBrokerageService: WoCustomsBrokerageService,
        protected woWorkOrderService: WoWorkOrderService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ woCustomsBrokerage }) => {
            this.updateForm(woCustomsBrokerage);
            this.woCustomsBrokerage = woCustomsBrokerage;
        });
        this.woWorkOrderService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IWoWorkOrder[]>) => mayBeOk.ok),
                map((response: HttpResponse<IWoWorkOrder[]>) => response.body)
            )
            .subscribe((res: IWoWorkOrder[]) => (this.woworkorders = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    updateForm(woCustomsBrokerage: IWoCustomsBrokerage) {
        this.editForm.patchValue({
            id: woCustomsBrokerage.id,
            monthlyImportShipment: woCustomsBrokerage.monthlyImportShipment,
            monthlyExportShipment: woCustomsBrokerage.monthlyExportShipment,
            shipmentValue: woCustomsBrokerage.shipmentValue,
            productInfo: woCustomsBrokerage.productInfo
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const woCustomsBrokerage = this.createFromForm();
        if (woCustomsBrokerage.id !== undefined) {
            this.subscribeToSaveResponse(this.woCustomsBrokerageService.update(woCustomsBrokerage));
        } else {
            this.subscribeToSaveResponse(this.woCustomsBrokerageService.create(woCustomsBrokerage));
        }
    }

    private createFromForm(): IWoCustomsBrokerage {
        const entity = {
            ...new WoCustomsBrokerage(),
            id: this.editForm.get(['id']).value,
            monthlyImportShipment: this.editForm.get(['monthlyImportShipment']).value,
            monthlyExportShipment: this.editForm.get(['monthlyExportShipment']).value,
            shipmentValue: this.editForm.get(['shipmentValue']).value,
            productInfo: this.editForm.get(['productInfo']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoCustomsBrokerage>>) {
        result.subscribe((res: HttpResponse<IWoCustomsBrokerage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackWoWorkOrderById(index: number, item: IWoWorkOrder) {
        return item.id;
    }
}
