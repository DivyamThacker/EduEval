import { Component, ViewEncapsulation } from '@angular/core';
import { FormsSidebarComponent } from '../../shared/forms-sidebar/forms-sidebar.component';
import { FormsTabsComponent } from '../../shared/forms-tabs/forms-tabs.component';
import { RouterModule } from '@angular/router';
import { FormsActionsComponent } from '../../shared/forms-actions/forms-actions.component';

@Component({
  selector: 'app-extended-profile',
  standalone: true,
  imports: [FormsSidebarComponent, FormsTabsComponent, FormsActionsComponent, RouterModule],
  templateUrl: './extended-profile.component.html',
  styleUrl: './extended-profile.component.css',
  encapsulation: ViewEncapsulation.None
})
export class ExtendedProfileComponent {
  extendedProfileSubSections = [
    { label: 'Program Details', route: '/extended-profile/program-details' },
    { label: 'Student Detials', route: '/extended-profile/student-details' },
    { label: 'Academic Details', route: '/extended-profile/academic-details' },
    { label: 'Institution Details', route: '/extended-profile/institution-details' }
  ];
}
