import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';
import { IShippingAddress } from 'app/shared/model/shipping-address.model';
import { ShippingAddressService } from 'app/entities/shipping-address';
import { IWoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from 'app/entities/wo-package-type';

@Component({
    selector: 'jhi-wo-work-order-update',
    templateUrl: './wo-work-order-update.component.html'
})
export class WoWorkOrderUpdateComponent implements OnInit {
    woWorkOrder: IWoWorkOrder;
    isSaving: boolean;

    shippingaddresses: IShippingAddress[];

    wopackagetypes: IWoPackageType[];
    dateCreated: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected woWorkOrderService: WoWorkOrderService,
        protected shippingAddressService: ShippingAddressService,
        protected woPackageTypeService: WoPackageTypeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ woWorkOrder }) => {
            this.woWorkOrder = woWorkOrder;
            this.dateCreated = this.woWorkOrder.dateCreated != null ? this.woWorkOrder.dateCreated.format(DATE_TIME_FORMAT) : null;
        });
        this.shippingAddressService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IShippingAddress[]>) => mayBeOk.ok),
                map((response: HttpResponse<IShippingAddress[]>) => response.body)
            )
            .subscribe((res: IShippingAddress[]) => (this.shippingaddresses = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.woPackageTypeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IWoPackageType[]>) => mayBeOk.ok),
                map((response: HttpResponse<IWoPackageType[]>) => response.body)
            )
            .subscribe((res: IWoPackageType[]) => (this.wopackagetypes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.woWorkOrder.dateCreated = this.dateCreated != null ? moment(this.dateCreated, DATE_TIME_FORMAT) : null;
        if (this.woWorkOrder.id !== undefined) {
            this.subscribeToSaveResponse(this.woWorkOrderService.update(this.woWorkOrder));
        } else {
            this.subscribeToSaveResponse(this.woWorkOrderService.create(this.woWorkOrder));
        }
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

    trackShippingAddressById(index: number, item: IShippingAddress) {
        return item.id;
    }

    trackWoPackageTypeById(index: number, item: IWoPackageType) {
        return item.id;
    }
}
