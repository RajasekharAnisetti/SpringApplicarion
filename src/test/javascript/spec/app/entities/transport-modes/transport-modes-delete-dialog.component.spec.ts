/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SpringApplicarionTestModule } from '../../../test.module';
import { TransportModesDeleteDialogComponent } from 'app/entities/transport-modes/transport-modes-delete-dialog.component';
import { TransportModesService } from 'app/entities/transport-modes/transport-modes.service';

describe('Component Tests', () => {
    describe('TransportModes Management Delete Component', () => {
        let comp: TransportModesDeleteDialogComponent;
        let fixture: ComponentFixture<TransportModesDeleteDialogComponent>;
        let service: TransportModesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SpringApplicarionTestModule],
                declarations: [TransportModesDeleteDialogComponent]
            })
                .overrideTemplate(TransportModesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransportModesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransportModesService);
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
