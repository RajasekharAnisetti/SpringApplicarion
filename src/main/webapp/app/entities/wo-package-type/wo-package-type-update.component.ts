import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IWoPackageType } from 'app/shared/model/wo-package-type.model';
import { WoPackageTypeService } from './wo-package-type.service';

@Component({
    selector: 'jhi-wo-package-type-update',
    templateUrl: './wo-package-type-update.component.html'
})
export class WoPackageTypeUpdateComponent implements OnInit {
    woPackageType: IWoPackageType;
    isSaving: boolean;

    constructor(protected woPackageTypeService: WoPackageTypeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ woPackageType }) => {
            this.woPackageType = woPackageType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.woPackageType.id !== undefined) {
            this.subscribeToSaveResponse(this.woPackageTypeService.update(this.woPackageType));
        } else {
            this.subscribeToSaveResponse(this.woPackageTypeService.create(this.woPackageType));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoPackageType>>) {
        result.subscribe((res: HttpResponse<IWoPackageType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
