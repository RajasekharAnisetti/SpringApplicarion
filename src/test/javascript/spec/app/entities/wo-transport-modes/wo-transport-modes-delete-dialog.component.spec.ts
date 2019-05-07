/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SpringApplicarionTestModule } from '../../../test.module';
import { WoTransportModesDeleteDialogComponent } from 'app/entities/wo-transport-modes/wo-transport-modes-delete-dialog.component';
import { WoTransportModesService } from 'app/entities/wo-transport-modes/wo-transport-modes.service';

describe('Component Tests', () => {
    describe('WoTransportModes Management Delete Component', () => {
        let comp: WoTransportModesDeleteDialogComponent;
        let fixture: ComponentFixture<WoTransportModesDeleteDialogComponent>;
        let service: WoTransportModesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [WoTransportModesDeleteDialogComponent]
            })
                .overrideTemplate(WoTransportModesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WoTransportModesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WoTransportModesService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
