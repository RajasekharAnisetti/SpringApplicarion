/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { SpringApplicarionTestModule } from '../../../test.module';
import { TransportModesUpdateComponent } from 'app/entities/transport-modes/transport-modes-update.component';
import { TransportModesService } from 'app/entities/transport-modes/transport-modes.service';
import { TransportModes } from 'app/shared/model/transport-modes.model';

describe('Component Tests', () => {
    describe('TransportModes Management Update Component', () => {
        let comp: TransportModesUpdateComponent;
        let fixture: ComponentFixture<TransportModesUpdateComponent>;
        let service: TransportModesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [TransportModesUpdateComponent],
                providers: [FormBuilder]
            })
                .overrideTemplate(TransportModesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TransportModesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransportModesService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new TransportModes(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.updateForm(entity);
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new TransportModes();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.updateForm(entity);
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
