import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoPackage } from 'app/shared/model/wo-package.model';
import { WoPackageService } from './wo-package.service';

@Component({
    selector: 'jhi-wo-package-delete-dialog',
    templateUrl: './wo-package-delete-dialog.component.html'
})
export class WoPackageDeleteDialogComponent {
    woPackage: IWoPackage;

    constructor(
        protected woPackageService: WoPackageService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.woPackageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'woPackageListModification',
                content: 'Deleted an woPackage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-wo-package-delete-popup',
    template: ''
})
export class WoPackageDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ woPackage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(WoPackageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.woPackage = woPackage;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/wo-package', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/wo-package', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
