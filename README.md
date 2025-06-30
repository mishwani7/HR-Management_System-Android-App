# cloral HR Management System Android Application

## Overview

cloral HR Management System is a comprehensive Android application designed to streamline and manage the recruitment process for companies. Built in Java, it leverages Firebase for cloud data management and local SQLite for offline support. The app enables HR teams to efficiently handle candidate information, track application statuses, and synchronize data securely between devices and the cloud.

## Features

- **User Authentication:**
  - Secure login using Google and Phone authentication via Firebase Auth.
  - Ensures only authorized HR personnel can access sensitive candidate data.

- **Candidate Management:**
  - Add, edit, and delete candidate records, including name, phone, position applied for, status, profile photo, and resume.
  - View detailed candidate profiles and update their information as needed.

- **Resume & Photo Upload:**
  - Upload and view candidate resumes in PDF format.
  - Capture or select candidate profile images using the device camera or gallery.
  - Images and resumes are stored securely in Firebase Storage.

- **Status Tracking:**
  - Track each candidate’s status: Selected, Rejected, In Process, or Interview.
  - Easily update statuses and filter candidates based on their current stage in the recruitment pipeline.

- **Sorting & Searching:**
  - Sort candidates by name, status, position, or time of application.
  - Powerful search functionality to quickly find candidates by name.

- **Cloud Sync:**
  - Push and retrieve candidate data and files to/from Firebase Realtime Database using a unique key.
  - Enables backup, restore, and multi-device access to the recruitment database.

- **Selected Candidates Management:**
  - Maintain a separate list of selected candidates for further processing or onboarding.
  - Sort and manage selected candidates independently from the main list.

- **Modern UI:**
  - Clean, intuitive interface using Material Design components.
  - Custom icons and color-coded cards for easy navigation and status recognition.

- **Offline Support:**
  - All candidate data is stored locally in an SQLite database for offline access.
  - Data can be synchronized with the cloud when connectivity is available.

## Technology Stack

- **Programming Language:** Java (Android)
- **Cloud Backend:** Firebase (Authentication, Realtime Database, Storage)
- **Local Database:** SQLite
- **Libraries Used:**
  - FirebaseUI (for authentication UI)
  - Picasso & Glide (for image loading and caching)
  - PickImage (for image selection/capture)
  - CircleImageView (for rounded profile images)

## App Structure

- **Activities:**
  - `HomeActivity`: Main dashboard with navigation to all features.
  - `NewCandidateActivity`: Add new candidates with photo and resume upload.
  - `AllApplicationsActivity`: View, search, sort, and manage all candidates.
  - `SelectedCandidatesActivity`: Manage the list of selected candidates.
  - `CandidateDetailsActivity`: View and edit detailed candidate information.
- **Database Handler:**
  - `DBhandler.java`: Handles all SQLite operations for local data storage.
- **Model:**
  - `Candidate.java`: Data model for candidate information.
- **Utilities:**
  - `Functions.java`: Helper functions for image processing and conversion.

## Setup & Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/mishwani7/HR-Management_System-Android-App.git
   ```
2. **Open the project in Android Studio.**
3. **Configure Firebase:**
   - Create a Firebase project in the [Firebase Console](https://console.firebase.google.com/).
   - Enable Authentication (Google and Phone), Realtime Database, and Storage.
   - Download your `google-services.json` and place it in the `app/` directory.
4. **Build the project:**
   - Let Gradle sync and download all dependencies.
   - Connect an Android device or start an emulator.
   - Click **Run** to build and launch the app.

## Usage Guide

- **Login:**
  - On first launch, sign in using Google or Phone authentication.
- **Add Candidate:**
  - Tap the yellow card to add a new candidate. Fill in details, upload a photo and resume, and save.
- **View & Edit Candidates:**
  - Tap the red card to view all applications. Tap a candidate to view or edit details.
- **Manage Selected Candidates:**
  - Tap the blue card to view and manage selected candidates.
- **Sync Data:**
  - Use the push/retrieve buttons in the All Applications screen to sync data with Firebase using a unique key.
- **Sort & Search:**
  - Use the sort and search features to organize and find candidates quickly.

## Permissions Required

- **Internet:** Required for Firebase authentication and cloud sync.
- **Read/Write External Storage:** Required for uploading and viewing resumes and profile images.

## Folder Structure

- `app/src/main/java/com/abu/hrms/` — Main source code (activities, DB handler, models, utilities)
- `app/src/main/res/` — Layouts, drawables, values, and resources
- `images/` — App screenshots and icons

## Customization & Extensibility

- You can add more candidate fields (e.g., email, address) by updating the model and UI.
- Integrate additional authentication providers or cloud features as needed.
- The app can be extended for onboarding, employee management, or analytics.

## Troubleshooting

- **Firebase Issues:** Ensure your `google-services.json` is correct and all Firebase services are enabled.
- **Build Errors:** Make sure all dependencies are downloaded and your Android Studio is up to date.
- **Permissions:** Grant all required permissions on your device for full functionality.

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change. Contributions for new features, bug fixes, and documentation improvements are appreciated.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

