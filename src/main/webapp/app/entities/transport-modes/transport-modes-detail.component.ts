import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransportModes } from 'app/shared/model/transport-modes.model';

@Component({
    selector: 'jhi-transport-modes-detail',
    templateUrl: './transport-modes-detail.component.html'
})
export class TransportModesDetailComponent implements OnInit {
    transportModes: ITransportModes;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transportModes }) => {
            this.transportModes = transportModes;
        });
    }

    previousState() {
        window.history.back();
    }
}
