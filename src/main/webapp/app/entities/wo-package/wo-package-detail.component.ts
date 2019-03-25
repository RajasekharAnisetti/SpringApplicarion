import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoPackage } from 'app/shared/model/wo-package.model';

@Component({
    selector: 'jhi-wo-package-detail',
    templateUrl: './wo-package-detail.component.html'
})
export class WoPackageDetailComponent implements OnInit {
    woPackage: IWoPackage;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ woPackage }) => {
            this.woPackage = woPackage;
        });
    }

    previousState() {
        window.history.back();
    }
}
