import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITransportModes, TransportModes } from 'app/shared/model/transport-modes.model';
import { TransportModesService } from './transport-modes.service';

@Component({
    selector: 'jhi-transport-modes-update',
    templateUrl: './transport-modes-update.component.html'
})
export class TransportModesUpdateComponent implements OnInit {
    transportModes: ITransportModes;
    isSaving: boolean;

    editForm = this.fb.group({
        id: [],
        name: [null, [Validators.maxLength(255)]]
    });

    constructor(
        protected transportModesService: TransportModesService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transportModes }) => {
            this.updateForm(transportModes);
            this.transportModes = transportModes;
        });
    }

    updateForm(transportModes: ITransportModes) {
        this.editForm.patchValue({
            id: transportModes.id,
            name: transportModes.name
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const transportModes = this.createFromForm();
        if (transportModes.id !== undefined) {
            this.subscribeToSaveResponse(this.transportModesService.update(transportModes));
        } else {
            this.subscribeToSaveResponse(this.transportModesService.create(transportModes));
        }
    }

    private createFromForm(): ITransportModes {
        const entity = {
            ...new TransportModes(),
            id: this.editForm.get(['id']).value,
            name: this.editForm.get(['name']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransportModes>>) {
        result.subscribe((res: HttpResponse<ITransportModes>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
