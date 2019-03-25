import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IWoPackage } from 'app/shared/model/wo-package.model';
import { WoPackageService } from './wo-package.service';
import { IWoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from 'app/entities/wo-package-type';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from 'app/entities/wo-work-order';

@Component({
    selector: 'jhi-wo-package-update',
    templateUrl: './wo-package-update.component.html'
})
export class WoPackageUpdateComponent implements OnInit {
    woPackage: IWoPackage;
    isSaving: boolean;

    wopackagetypes: IWoPackageType[];

    woworkorders: IWoWorkOrder[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected woPackageService: WoPackageService,
        protected woPackageTypeService: WoPackageTypeService,
        protected woWorkOrderService: WoWorkOrderService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ woPackage }) => {
            this.woPackage = woPackage;
        });
        this.woPackageTypeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IWoPackageType[]>) => mayBeOk.ok),
                map((response: HttpResponse<IWoPackageType[]>) => response.body)
            )
            .subscribe((res: IWoPackageType[]) => (this.wopackagetypes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.woWorkOrderService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IWoWorkOrder[]>) => mayBeOk.ok),
                map((response: HttpResponse<IWoWorkOrder[]>) => response.body)
            )
            .subscribe((res: IWoWorkOrder[]) => (this.woworkorders = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.woPackage.id !== undefined) {
            this.subscribeToSaveResponse(this.woPackageService.update(this.woPackage));
        } else {
            this.subscribeToSaveResponse(this.woPackageService.create(this.woPackage));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoPackage>>) {
        result.subscribe((res: HttpResponse<IWoPackage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackWoPackageTypeById(index: number, item: IWoPackageType) {
        return item.id;
    }

    trackWoWorkOrderById(index: number, item: IWoWorkOrder) {
        return item.id;
    }
}
