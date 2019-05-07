import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITransportModes } from 'app/shared/model/transport-modes.model';
import { AccountService } from 'app/core';
import { TransportModesService } from './transport-modes.service';

@Component({
    selector: 'jhi-transport-modes',
    templateUrl: './transport-modes.component.html'
})
export class TransportModesComponent implements OnInit, OnDestroy {
    transportModes: ITransportModes[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected transportModesService: TransportModesService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.transportModesService
            .query()
            .pipe(
                filter((res: HttpResponse<ITransportModes[]>) => res.ok),
                map((res: HttpResponse<ITransportModes[]>) => res.body)
            )
            .subscribe(
                (res: ITransportModes[]) => {
                    this.transportModes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTransportModes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITransportModes) {
        return item.id;
    }

    registerChangeInTransportModes() {
        this.eventSubscriber = this.eventManager.subscribe('transportModesListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
