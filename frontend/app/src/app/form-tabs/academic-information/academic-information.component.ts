import { Component, ViewEncapsulation } from '@angular/core';
import { FormsSidebarComponent } from '../../shared/forms-sidebar/forms-sidebar.component';
import { FormsTabsComponent } from '../../shared/forms-tabs/forms-tabs.component';
import { RouterModule } from '@angular/router';
import { FormsActionsComponent } from '../../shared/forms-actions/forms-actions.component';

@Component({
  selector: 'app-academic-information',
  standalone: true,
  imports: [FormsSidebarComponent, FormsTabsComponent, FormsActionsComponent, RouterModule],
  templateUrl: './academic-information.component.html',
  styleUrl: './academic-information.component.css',
  encapsulation: ViewEncapsulation.None
})
export class AcademicInformationComponent {
  academicInfoSubSections = [
    { label: 'College Information', route: '/academic-information/college-info' },
    { label: 'Teaching Faculty', route: '/academic-information/teaching-faculty' },
    { label: 'Non Teaching Staff', route: '/academic-information/non-teaching-staff' },
    { label: 'Student Details', route: '/academic-information/student-details' },
    { label: 'Extra University Details', route: '/academic-information/extra-uni-details' }
  ];
}
