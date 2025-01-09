import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ExampleComponent } from './example.component'; // Import the component to test
import { By } from '@angular/platform-browser'; // For accessing DOM elements in the tests
import { DebugElement } from '@angular/core';

describe('ExampleComponent', () => {
  let component: ExampleComponent;
  let fixture: ComponentFixture<ExampleComponent>;
  let button: DebugElement;

  beforeEach(async () => {
    // Set up the testing environment
    await TestBed.configureTestingModule({
      declarations: [ ExampleComponent ] // Declare the component to test
    })
    .compileComponents();
  });

  beforeEach(() => {
    // Create the component fixture (instance of the component and its template)
    fixture = TestBed.createComponent(ExampleComponent);
    component = fixture.componentInstance; // Get the component instance

    // Trigger change detection to initialize component data
    fixture.detectChanges();

    // Get the button element to simulate a click in the tests
    button = fixture.debugElement.query(By.css('button'));
  });

  it('should create the component', () => {
    // Test if the component is successfully created
    expect(component).toBeTruthy();
  });

  it('should display the initial title', () => {
    // Test if the initial title is rendered in the template
    const titleElement = fixture.debugElement.query(By.css('h1')).nativeElement;
    expect(titleElement.textContent).toBe('Welcome to my Angular Component!');
  });

  it('should change the title when the button is clicked', () => {
    // Simulate a button click
    button.triggerEventHandler('click', null);

    // Check if the title is updated after the button click
    fixture.detectChanges(); // Detect changes after the event
    const titleElement = fixture.debugElement.query(By.css('h1')).nativeElement;
    expect(titleElement.textContent).toBe('Title Changed!');
  });

});
