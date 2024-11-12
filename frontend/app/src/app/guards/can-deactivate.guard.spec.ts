import { TestBed } from '@angular/core/testing';
import { CanDeactivateFn } from '@angular/router';
import { canDeactivateGuard } from './can-deactivate.guard';
import { CanComponentDeactivate } from './can-deactivate.guard'; // Import the interface

describe('canDeactivateGuard', () => {
  // Explicitly type the guardParameters to match the CanDeactivateFn signature
  const executeGuard: CanDeactivateFn<CanComponentDeactivate> = (component, currentRoute, currentState, nextState) =>
    TestBed.runInInjectionContext(() => canDeactivateGuard(component, currentRoute, currentState, nextState));
  // const executeGuard: CanDeactivateFn<CanComponentDeactivate> = (...guardParameters: [CanComponentDeactivate]) =>
  //   TestBed.runInInjectionContext(() => canDeactivateGuard(...guardParameters));
  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
