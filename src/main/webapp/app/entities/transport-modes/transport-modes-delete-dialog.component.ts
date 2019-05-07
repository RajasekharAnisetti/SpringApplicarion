import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransportModes } from 'app/shared/model/transport-modes.model';
import { TransportModesService } from './transport-modes.service';

@Component({
    selector: 'jhi-transport-modes-delete-dialog',
    templateUrl: './transport-modes-delete-dialog.component.html'
})
export class TransportModesDeleteDialogComponent {
    transportModes: ITransportModes;

    constructor(
        protected transportModesService: TransportModesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.transportModesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'transportModesListModification',
                content: 'Deleted an transportModes'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-transport-modes-delete-popup',
    template: ''
})
export class TransportModesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transportModes }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TransportModesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.transportModes = transportModes;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/transport-modes', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/transport-modes', { outlets: { popup: null } }]);
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
