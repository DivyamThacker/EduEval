import { CanDeactivateFn } from '@angular/router';

// The interface that your component must implement to confirm if it can deactivate (leave the route)
export interface CanComponentDeactivate {
  canDeactivate: () => boolean | Promise<boolean>;
}

export const canDeactivateGuard: CanDeactivateFn<CanComponentDeactivate> = (component) => {
  return component.canDeactivate ? component.canDeactivate() : true;
};
