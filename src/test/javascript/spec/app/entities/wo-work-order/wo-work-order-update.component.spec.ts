/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SpringApplicarionTestModule } from '../../../test.module';
import { WoWorkOrderUpdateComponent } from 'app/entities/wo-work-order/wo-work-order-update.component';
import { WoWorkOrderService } from 'app/entities/wo-work-order/wo-work-order.service';
import { WoWorkOrder } from 'app/shared/model/wo-work-order.model';

describe('Component Tests', () => {
    describe('WoWorkOrder Management Update Component', () => {
        let comp: WoWorkOrderUpdateComponent;
        let fixture: ComponentFixture<WoWorkOrderUpdateComponent>;
        let service: WoWorkOrderService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [WoWorkOrderUpdateComponent]
            })
                .overrideTemplate(WoWorkOrderUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(WoWorkOrderUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WoWorkOrderService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new WoWorkOrder(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.woWorkOrder = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new WoWorkOrder();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.woWorkOrder = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
