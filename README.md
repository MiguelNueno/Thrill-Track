# Thrill Track 🎢

**Thrill Track** is a digital queue and ticketing management system designed for amusement parks. Built with Object-Oriented Programming (OOP) principles and structured around the Model-View-Controller (MVC) architecture, the system efficiently handles ride queues, manages ticket transactions, and offers priority boarding for VIP guests using a custom double-ended queue (Deque).

---

## Attribution

| Class | People Who Contributed | GenAI usage |
|--------|------------|---------------|
| Ride   | David Sage | No GenAI used |
| Deque  | David Sage | No GenAI used |
| Person | David Sage | No GenAI used |
| Wallet | David Sage | No GenAI used |
| RideTest | Nana Ababio | No GenAI used |
| DequeTest | Nana Ababio | No GenAI used |
| PersonTest | Nana Ababio | No GenAI used |
| WalletTest | Nana Ababio | No GenAI used |
| README | Miguel Nueno Armisén | No GenAI used|
| Reflection | David Sage, Miguel Nueno Armisén | No GenAI used |
| View | David Sage, Miguel Nueno Armisén | No GenAI used |

## System Overview & Architecture

### MVC Role Division

The application strictly separates responsibilities to maintain a clean boundary between data/state, output presentation, and control logic:

| Role | Class Name | Responsibilities |
| :--- | :--- | :--- |
| **Model** | `Person` | Stores individual guest profile data (ID, name, age, height, VIP status, queue flag). |
| **Model** | `Wallet` | Tracks guest cash and ticket balances; handles purchase validation math. |
| **Model** | `Ride` | Stores ride attributes (name, ticket cost, capacity, height requirement) and manages waiting guests. |
| **Model** | `Deque` | Custom doubly-linked data structure enabling O(1) front and back enqueueing/dequeueing. |
| **View** | `MainMenu` | Handles presentation logic; renders console screens, status reports, and error messages to output. |
| **Controller** | `ParkController` | Orchestrates flow; processes keyboard commands, performs input validation, and mutates models. |

---

## Key Features

* **Priority VIP Line Skipping:** VIP guests skip directly to the head of the line using `pushFront()`, while standard guests enter at the rear via `pushBack()`.
* **Ticket & Financial Management:** Integrated `Wallet` tracks cash and ticket balances, preventing unauthorized ticket purchases or unbudgeted ride entry.
* **Batch Line Dispatching:** Running a ride removes up to `capacity` guests from the front of the queue using `popFront()`.
* **Defensive Input Validation:** Rejects invalid ride lookups, guests under safety height requirements, insufficient ticket balances, and double-queue attempts.
* **Ride Revenue & Analytics:** Tracks cumulative riders served, tickets collected, and dollar revenue per ride as well as park-wide totals, accessible through the main menu.

---

## Input Validation Matrix

| Edge Case / Bad Input | Catching Location | Handling Strategy |
| :--- | :--- | :--- |
| **Invalid Ride Name** | `ParkController.findRide()` | Returns `null`. Displays error message and reprompts user. |
| **Insufficient Cash** | `Wallet.buyTickets()` | Returns `false`. Cash and ticket balances remain unchanged. |
| **Insufficient Tickets** | `ParkController.handleJoinQueue()` | Prevents ride entry and prompts user to purchase more tickets. |
| **Height Requirement Unmet** | `ParkController.handleJoinQueue()` | Compares guest height to ride minimum; rejects entry on safety grounds. |
| **Already In Queue** | `Person.isInQueue()` | Checks active status flag to prevent a guest from joining multiple queues. |
| **Empty Queue Removal** | `Deque.popFront()` | Throws `NoSuchElementException` safely handled by controller. |

---

## Getting Started

### Prerequisites
* **Java Development Kit (JDK):** Version 11 or higher
* **IDE / Terminal:** IntelliJ IDEA, Eclipse, VS Code, or standard command line

### Compilation & Execution

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/Thrill-Track.git](https://github.com/your-username/Thrill-Track.git)
   cd Thrill-Track

## Reflection

### How did our design absorb the change?

For Deliverable 4, we implemented Ride Revenue & Analytics from our Deliverable 3 Next Steps list. This feature touched four files:

Ride.java (Model): Added totalRidersServed and totalTicketsCollected fields, initialized them in the constructor, incremented both inside runRide(), and exposed getters (getTotalRidersServed(), getTotalTicketsCollected()).

ParkController.java (Controller): Added showAnalytics() to iterate over availableRides, compute total park riders/tickets/dollar revenue using TICKET_PRICE, and pass the data to the view, plus wired option 5 in start().

MainMenu.java (View): Added 5. View Ride Analytics to displayMenu() (moving Exit to 6) and added displayRideAnalytics() and displayAnalyticsSummary() to format and print the table.

### Did the change cross the model/view line?

No—the model/view boundary remained intact and actually improved compared to Deliverable 3:

Ride holds only raw integer counts (totalRidersServed and totalTicketsCollected) and has zero awareness of dollar prices (TICKET_PRICE), console formatting, or MainMenu.

MainMenu does not hold references to Ride or Deque objects; ParkController.showAnalytics() extracts primitive values (String, int, double) from the models and passes them to MainMenu.displayRideAnalytics(...).

Addressing our D3 reflection note about formatting leaking into the controller, we also moved status formatting (displayGuestStatus(...)) out of ParkController and into MainMenu.

### What changed from our D3 plan?

Feature Selection: Our D3 Next Steps listed five stretch goals (Hotel Key Pass, Automated Ride Simulation, Ride Revenue & Analytics, Group/Bundle Purchases, and Estimated Wait Times). We selected Ride Revenue & Analytics as our single D4 change feature because it builds directly on Ride.runRide() without introducing multithreading concurrency issues or complex multi-person transaction rollbacks.

Model Validation: We tightened Ride constructor and addPerson() validation (IllegalArgumentException on blank names, non-positive capacity, negative cost, or null guests) so bad inputs are caught at the model boundary as well as in ParkController.

Controller/View Balance: We shifted menu option Exit from 5 to 6 to accommodate 5. View Ride Analytics and moved status printf formatting from ParkController into MainMenu.

### What doesn't work yet?

Guest Switching / Multi-Guest Sessions: Currently, ParkController creates a single interactive activeGuest at startup alongside three static background guests (Alex, Sam, Jordan). Users cannot switch between multiple interactive guests or register new guests mid-session.

Persistence: Ride analytics and guest balances reset when the program exits; data is not yet saved to disk.

Remaining Stretch Goals: Estimated Wait Times, Group/Bundle Purchases, Hotel Key Pass Integration, and Automated Ride Simulation are not yet implemented.

## Next Steps

### What is left for the final build?

- Multi-Guest / Guest-Switching Support: Add a menu option allowing the user to create or switch active guests mid-session so VIP vs. standard queue ordering (addToFront vs. addToBack) can be demonstrated live with multiple interactive guests.

- Estimated Wait Time Display: Add a rideDurationMinutes attribute to Ride and display real-time estimated wait times alongside queue lengths in MainMenu.displayRideStatus(...).

- Full Bad-Input & Boundary Audit: Expand automated JUnit coverage across PersonTest, WalletTest, and DequeTest to verify every edge case in the Input Validation Matrix, and test extreme inputs (e.g., integer overflow on ticket purchases, whitespace-only ride names) in the console loop.

- Code Cleanup & Javadoc Polish: Remove any leftover placeholder comments and ensure consistent Javadoc comments across all model, view, and controller classes.

### Stretch Goals Commitment

Committing to:

Estimated Wait Times: Low risk and high demo value; uses Deque.size(), Ride.capacity, and a ride cycle duration to show guests how long a line will take before joining.

Multi-Guest / Group Queueing (Simplified): Allowing multiple guests to be created and queued in one session to showcase VIP priority line-skipping in real time.

Dropping:

Automated Ride Simulation: Dropped because background timer threads introduce race conditions with Scanner console input and Deque mutations that add unnecessary instability for a console app.

Hotel Key Pass Integration: Dropped to keep scope focused on core park ticketing, queue priority, and ride analytics rather than external hotel room state.

### 3-Minute Demo Walkthrough

Guest Creation & Initial State (~30 sec)
Ticket Purchase & Bad-Input Recovery (~45 sec)
VIP Priority Queueing & Validation (~45 sec)
Ride Dispatch & Live Analytics Update (~60 sec)


