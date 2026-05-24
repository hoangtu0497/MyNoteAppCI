# 📝 Note App

Hi, and thank you for taking the time to review my project.

For this app, I’ve decided to go with the **Package by Component** modularization approach. While the project itself is relatively small, much larger projects can benefit from this approach. Each component encapsulates its own **domain** and **data** layers, allowing multiple UI modules to share them effectively.

## 🧪 Testing
To demonstrate the testability of this architecture, I’ve written a few test classes that cover different layers of the app:
* `RealAddNoteViewModelTest`
* `RealNoteRepositoryTest`
* `AddNoteUseCaseTest`

## 🧰 Dummy Data for Pagination
I have created a convenience block of code that you can use to insert dummy data into the database, after that pagination can be tested.<br>
You can find it inside the `RealNoteRepository` class.

## ⚠️ Known Limitation
There's a minor issue with the scrollable views in the **Add/Edit Note** screens. If the user enters a large amount of text into the text fields, the view doesn't automatically scroll to keep up. Manual scrolling is still possible, and all functionalities remain accessible.<br>
I was running out of time, I will leave this as a limitation, though you should be able to use all the functionalities just fine.

Again, thanks for your time and feedback.

**Tate**
