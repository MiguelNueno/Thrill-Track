# Thrill Track 🎢

**Thrill Track** is a digital queue and ticketing management system designed for amusement parks. Built with Object-Oriented Programming (OOP) principles and structured around the Model-View-Controller (MVC) architecture, the system efficiently handles ride queues, manages ticket transactions, and offers priority boarding for VIP guests using a custom double-ended queue (Deque).

---

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

Possible next steps:
- Hotel Key Pass Integration: Link a guest's hotel room key card to automatically grant VIP line privileges and complimentary tickets upon check-in.
- Automated Ride Simulation: Add background timers to run rides automatically at fixed intervals and progress queues dynamically.
- Ride Revenue & Analytics: Track and display real-time revenue and ticket usage per ride for park administrators.
- Group & Bundle Purchases: Allow families or groups to purchase ticket bundles and join queues together.
- Estimated Wait Times: Calculate real-time estimated wait times based on ride capacity and current line length.
