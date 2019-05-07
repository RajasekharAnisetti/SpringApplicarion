import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IWoWorkOrder, WoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';
import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';
import { WoCustomsBrokerageService } from 'app/entities/wo-customs-brokerage';

@Component({
    selector: 'jhi-wo-work-order-update',
    templateUrl: './wo-work-order-update.component.html'
})
export class WoWorkOrderUpdateComponent implements OnInit {
    woWorkOrder: IWoWorkOrder;
    isSaving: boolean;

    wocustomsbrokerages: IWoCustomsBrokerage[];
    shippingDateDp: any;

    editForm = this.fb.group({
        id: [],
        customsBrokerName: [null, [Validators.maxLength(255)]],
        customsContactName: [null, [Validators.maxLength(255)]],
        customsCurrency: [null, [Validators.maxLength(255)]],
        customsPhoneNumber: [null, [Validators.maxLength(255)]],
        customsValue: [],
        fromAirportCode: [null, [Validators.maxLength(255)]],
        fromLocationType: [null, [Validators.max(11)]],
        jobContentDescr: [null, [Validators.maxLength(255)]],
        shipQuantity: [null, [Validators.max(11)]],
        jobOriginalCost: [null, [Validators.max(11)]],
        quotedAmount: [null, [Validators.maxLength(255)]],
        assignTo: [null, [Validators.maxLength(255)]],
        toCompany: [null, [Validators.maxLength(255)]],
        toLocationType: [null, [Validators.max(11)]],
        dateCreated: [],
        jobNumber: [null, [Validators.maxLength(255)]],
        jobCustomer: [null, [Validators.maxLength(255)]],
        serviceType: [],
        jobSales: [null, [Validators.maxLength(255)]],
        jobDeadlineTime: [],
        jobTimeZone: [],
        shippingDate: [],
        shippingTime: [],
        insuranceType: [],
        shipTotalWeight: [null, [Validators.maxLength(255)]],
        shipmentCurrency: [null, [Validators.maxLength(255)]],
        shipmentMetric: [null, [Validators.maxLength(255)]],
        woRequestType: [null, [Validators.max(11)]],
        woCustomsBrokerageId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected woWorkOrderService: WoWorkOrderService,
        protected woCustomsBrokerageService: WoCustomsBrokerageService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ woWorkOrder }) => {
            this.updateForm(woWorkOrder);
            this.woWorkOrder = woWorkOrder;
        });
        this.woCustomsBrokerageService
            .query({ filter: 'woworkorder-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IWoCustomsBrokerage[]>) => mayBeOk.ok),
                map((response: HttpResponse<IWoCustomsBrokerage[]>) => response.body)
            )
            .subscribe(
                (res: IWoCustomsBrokerage[]) => {
                    if (!this.woWorkOrder.woCustomsBrokerageId) {
                        this.wocustomsbrokerages = res;
                    } else {
                        this.woCustomsBrokerageService
                            .find(this.woWorkOrder.woCustomsBrokerageId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IWoCustomsBrokerage>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IWoCustomsBrokerage>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IWoCustomsBrokerage) => (this.wocustomsbrokerages = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    updateForm(woWorkOrder: IWoWorkOrder) {
        this.editForm.patchValue({
            id: woWorkOrder.id,
            customsBrokerName: woWorkOrder.customsBrokerName,
            customsContactName: woWorkOrder.customsContactName,
            customsCurrency: woWorkOrder.customsCurrency,
            customsPhoneNumber: woWorkOrder.customsPhoneNumber,
            customsValue: woWorkOrder.customsValue,
            fromAirportCode: woWorkOrder.fromAirportCode,
            fromLocationType: woWorkOrder.fromLocationType,
            jobContentDescr: woWorkOrder.jobContentDescr,
            shipQuantity: woWorkOrder.shipQuantity,
            jobOriginalCost: woWorkOrder.jobOriginalCost,
            quotedAmount: woWorkOrder.quotedAmount,
            assignTo: woWorkOrder.assignTo,
            toCompany: woWorkOrder.toCompany,
            toLocationType: woWorkOrder.toLocationType,
            dateCreated: woWorkOrder.dateCreated != null ? woWorkOrder.dateCreated.format(DATE_TIME_FORMAT) : null,
            jobNumber: woWorkOrder.jobNumber,
            jobCustomer: woWorkOrder.jobCustomer,
            serviceType: woWorkOrder.serviceType,
            jobSales: woWorkOrder.jobSales,
            jobDeadlineTime: woWorkOrder.jobDeadlineTime,
            jobTimeZone: woWorkOrder.jobTimeZone,
            shippingDate: woWorkOrder.shippingDate,
            shippingTime: woWorkOrder.shippingTime,
            insuranceType: woWorkOrder.insuranceType,
            shipTotalWeight: woWorkOrder.shipTotalWeight,
            shipmentCurrency: woWorkOrder.shipmentCurrency,
            shipmentMetric: woWorkOrder.shipmentMetric,
            woRequestType: woWorkOrder.woRequestType,
            woCustomsBrokerageId: woWorkOrder.woCustomsBrokerageId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const woWorkOrder = this.createFromForm();
        if (woWorkOrder.id !== undefined) {
            this.subscribeToSaveResponse(this.woWorkOrderService.update(woWorkOrder));
        } else {
            this.subscribeToSaveResponse(this.woWorkOrderService.create(woWorkOrder));
        }
    }

    private createFromForm(): IWoWorkOrder {
        const entity = {
            ...new WoWorkOrder(),
            id: this.editForm.get(['id']).value,
            customsBrokerName: this.editForm.get(['customsBrokerName']).value,
            customsContactName: this.editForm.get(['customsContactName']).value,
            customsCurrency: this.editForm.get(['customsCurrency']).value,
            customsPhoneNumber: this.editForm.get(['customsPhoneNumber']).value,
            customsValue: this.editForm.get(['customsValue']).value,
            fromAirportCode: this.editForm.get(['fromAirportCode']).value,
            fromLocationType: this.editForm.get(['fromLocationType']).value,
            jobContentDescr: this.editForm.get(['jobContentDescr']).value,
            shipQuantity: this.editForm.get(['shipQuantity']).value,
            jobOriginalCost: this.editForm.get(['jobOriginalCost']).value,
            quotedAmount: this.editForm.get(['quotedAmount']).value,
            assignTo: this.editForm.get(['assignTo']).value,
            toCompany: this.editForm.get(['toCompany']).value,
            toLocationType: this.editForm.get(['toLocationType']).value,
            dateCreated:
                this.editForm.get(['dateCreated']).value != null
                    ? moment(this.editForm.get(['dateCreated']).value, DATE_TIME_FORMAT)
                    : undefined,
            jobNumber: this.editForm.get(['jobNumber']).value,
            jobCustomer: this.editForm.get(['jobCustomer']).value,
            serviceType: this.editForm.get(['serviceType']).value,
            jobSales: this.editForm.get(['jobSales']).value,
            jobDeadlineTime: this.editForm.get(['jobDeadlineTime']).value,
            jobTimeZone: this.editForm.get(['jobTimeZone']).value,
            shippingDate: this.editForm.get(['shippingDate']).value,
            shippingTime: this.editForm.get(['shippingTime']).value,
            insuranceType: this.editForm.get(['insuranceType']).value,
            shipTotalWeight: this.editForm.get(['shipTotalWeight']).value,
            shipmentCurrency: this.editForm.get(['shipmentCurrency']).value,
            shipmentMetric: this.editForm.get(['shipmentMetric']).value,
            woRequestType: this.editForm.get(['woRequestType']).value,
            woCustomsBrokerageId: this.editForm.get(['woCustomsBrokerageId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoWorkOrder>>) {
        result.subscribe((res: HttpResponse<IWoWorkOrder>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackWoCustomsBrokerageById(index: number, item: IWoCustomsBrokerage) {
        return item.id;
    }
}
