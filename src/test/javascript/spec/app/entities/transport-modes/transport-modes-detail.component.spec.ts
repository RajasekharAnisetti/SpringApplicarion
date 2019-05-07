/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpringApplicarionTestModule } from '../../../test.module';
import { TransportModesDetailComponent } from 'app/entities/transport-modes/transport-modes-detail.component';
import { TransportModes } from 'app/shared/model/transport-modes.model';

describe('Component Tests', () => {
    describe('TransportModes Management Detail Component', () => {
        let comp: TransportModesDetailComponent;
        let fixture: ComponentFixture<TransportModesDetailComponent>;
        const route = ({ data: of({ transportModes: new TransportModes(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [TransportModesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TransportModesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransportModesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.transportModes).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
