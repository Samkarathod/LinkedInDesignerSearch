# LinkedIn Designer Scraper

This is a mini Selenium automation project that logs into LinkedIn, searches for profiles with the keyword "Designer", scrapes names, and saves them to a CSV file. It also captures a screenshot of the results page.

## 🚀 Features
- Automated login to LinkedIn
- Searches for "Designer" keyword
- Scrapes names of people in search results
- Saves results to `designer_search_results.csv`
- Captures a screenshot of the result page

## 🛠 Tech Stack
- Java
- Selenium WebDriver
- ChromeDriver

## 🔐 How to Run
> You must have Java and Selenium WebDriver installed to run this code.

1. Set your LinkedIn credentials as environment variables:
   ```
   LINKEDIN_EMAIL
   LINKEDIN_PASSWORD
   ```

2. Place `chromedriver.exe` in a folder named `drivers`

3. Run using your IDE or terminal:
   ```
   javac LinkedInDesignerSearch.java
   java LinkedInDesignerSearch
   ```

## 📂 Output
- `designer_search_results.csv` — List of profile names
- `designer_search_screenshot.png` — Screenshot of LinkedIn search page

> ⚠️ This project is for educational purposes only.