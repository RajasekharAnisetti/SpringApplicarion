import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoPackage } from 'app/shared/model/wo-package.model';
import { AccountService } from 'app/core';
import { WoPackageService } from './wo-package.service';

@Component({
    selector: 'jhi-wo-package',
    templateUrl: './wo-package.component.html'
})
export class WoPackageComponent implements OnInit, OnDestroy {
    woPackages: IWoPackage[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected woPackageService: WoPackageService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.woPackageService
            .query()
            .pipe(
                filter((res: HttpResponse<IWoPackage[]>) => res.ok),
                map((res: HttpResponse<IWoPackage[]>) => res.body)
            )
            .subscribe(
                (res: IWoPackage[]) => {
                    this.woPackages = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInWoPackages();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IWoPackage) {
        return item.id;
    }

    registerChangeInWoPackages() {
        this.eventSubscriber = this.eventManager.subscribe('woPackageListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
