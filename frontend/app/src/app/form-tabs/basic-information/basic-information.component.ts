import { Component, ViewEncapsulation } from '@angular/core';
import { FormsSidebarComponent } from '../../shared/forms-sidebar/forms-sidebar.component';
import { FormsTabsComponent } from '../../shared/forms-tabs/forms-tabs.component';
import { RouterModule } from '@angular/router';
import { FormsActionsComponent } from '../../shared/forms-actions/forms-actions.component';
import { BasicInfoComponent } from './basic-info/basic-info.component';

@Component({
  selector: 'app-basic-information',
  standalone: true,
  imports: [FormsSidebarComponent, FormsTabsComponent, FormsActionsComponent,BasicInfoComponent, RouterModule],
  templateUrl: './basic-information.component.html',
  styleUrl: './basic-information.component.css',
  encapsulation: ViewEncapsulation.None
})
export class BasicInformationComponent {
  basicInfoSubSections = [
    { label: 'Basic Info', route: '/basic-information/basic-info' },
    { label: 'Contact Details', route: '/basic-information/contact-details' },
    { label: 'Recognition Details', route: '/basic-information/recognition-details' },
    { label: 'Area & Location', route: '/basic-information/area-location' }
  ];
}
