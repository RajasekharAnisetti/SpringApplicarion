/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SpringApplicarionTestModule } from '../../../test.module';
import { TransportModesComponent } from 'app/entities/transport-modes/transport-modes.component';
import { TransportModesService } from 'app/entities/transport-modes/transport-modes.service';
import { TransportModes } from 'app/shared/model/transport-modes.model';

describe('Component Tests', () => {
    describe('TransportModes Management Component', () => {
        let comp: TransportModesComponent;
        let fixture: ComponentFixture<TransportModesComponent>;
        let service: TransportModesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [TransportModesComponent],
                providers: []
            })
                .overrideTemplate(TransportModesComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransportModesComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransportModesService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TransportModes(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.transportModes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
