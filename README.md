# Quarkus Roq Website - Technical Documentation

## Overview

This is a static site generator (SSG) built with **Quarkus Roq**, combining the power of Java/Quarkus with modern web development practices. Roq is a thin layer on top of Quarkus that provides static site generation capabilities while leveraging the entire Quarkus ecosystem.

**Project:** blog-eisele-net-roq  
**Version:** 1.0.0-SNAPSHOT  
**Java Version:** 21  
**Quarkus Version:** 3.30.2  
**Roq Version:** 2.0.3

## What is Roq?

Roq is a Static Site Generator that runs on Quarkus. Unlike traditional SSGs that need to rebuild their entire framework, Roq leverages existing Quarkus features:

- **Qute** - Type-safe template engine
- **CDI** - Dependency injection for extending functionality
- **Quarkus Extensions** - Roq plugins and themes are Quarkus extensions
- **Dev Mode** - Live reload and hot deployment during development
- **Web Bundler** - Asset bundling without configuration
- **Test Framework** - Built-in testing capabilities

## Project Structure

```
blog-eisele-net-roq/
├── content/                          # Content files (pages & posts)
│   ├── index.html                    # Homepage with site metadata
│   ├── about.md                      # About page
│   ├── tags.html                     # Tags listing page
│   └── posts/                        # Blog posts directory
│       ├── 2025-01-01-welcome-to-your-blog.md
│       └── 2025-01-02-demo/
│           ├── index.md              # Post content
│           └── *.png                 # Post images
│
├── templates/                        # Qute templates
│   ├── layouts/                      # Page layouts
│   │   ├── default.html              # Base HTML structure
│   │   ├── page.html                 # Layout for pages
│   │   ├── post.html                 # Layout for blog posts
│   │   └── tag.html                  # Layout for tag pages
│   ├── partials/                     # Reusable components
│   │   ├── header.html               # Site header
│   │   ├── posts-paginator.html      # Pagination component
│   │   └── toc.html                  # Table of contents
│   └── tags/                         # Custom Qute tags
│       └── postCard.html             # Post card component
│
├── web/                              # Frontend assets (auto-bundled)
│   ├── app.js                        # Main JavaScript
│   ├── style.css                     # TailwindCSS styles
│   ├── theme-toggle.js               # Dark/light mode toggle
│   └── toc.js                        # Table of contents functionality
│
├── public/                           # Static assets
│   └── images/
│       └── favicon.ico
│
├── config/                           # Configuration
│   └── application.properties        # Quarkus/Roq configuration
│
├── .github/workflows/                # CI/CD
│   └── deploy.yml                    # GitHub Pages deployment
│
├── pom.xml                           # Maven dependencies
└── target/                           # Build output (generated)
    ├── roq-templates/                # Processed templates
    └── web-bundler/                  # Bundled assets
```

## Core Technologies

### 1. Quarkus (3.30.2)
The foundation framework providing:
- Fast startup and low memory footprint
- Dev mode with live reload
- Extension ecosystem
- Build-time optimization

### 2. Roq (2.0.3)
Static site generation layer providing:
- Convention-based routing from file structure
- FrontMatter parsing (YAML metadata in content files)
- Collection management (posts, pages)
- Plugin system (faker, tagging)
- Static export command

### 3. Qute Template Engine
Type-safe templating with:
- `{#insert /}` - Content insertion points
- `{#include partials/header.html /}` - Include partials
- `{#for item in collection}` - Loops
- `{#if condition}` - Conditionals
- `{site.data.property}` - Data access
- `{#postCard post site/}` - Custom tags

### 4. Quarkus Web Bundler (2.0.4)
Asset management providing:
- Automatic JavaScript bundling
- TailwindCSS processing
- NPM dependency management via mvnpm
- Live reload for assets
- Production optimization

### 5. TailwindCSS
Utility-first CSS framework with:
- Custom theme configuration
- Dark mode support
- Typography plugin
- Responsive design utilities

## How It Works

### Content Management

#### FrontMatter
Every content file starts with YAML metadata between `---` markers:

```yaml
---
title: "Post Title"
date: 2025-01-01 10:00:00 +0000
tags: [tag1, tag2, tag3]
description: "Brief description"
layout: post
toc: true
---
```

**Common FrontMatter Fields:**
- `title` - Page/post title
- `date` - Publication date (for posts)
- `tags` - Array of tags
- `description` - Meta description and excerpt
- `layout` - Template layout to use (default, post, page, tag)
- `toc` - Enable table of contents
- `paginate` - Enable pagination for collections

#### Content Files

**Pages** (`content/*.md` or `*.html`):
- Accessible at `/{filename}/`
- Use `layout: page` or `layout: default`
- Example: `content/about.md` → `/about/`

**Posts** (`content/posts/*.md`):
- Automatically added to blog collection
- Accessible at `/posts/{slug}/`
- Slug derived from title or filename
- Support for post directories with `index.md` for co-located assets
- Example: `content/posts/2025-01-02-demo/index.md`

**Index Page** (`content/index.html`):
- Site homepage
- Contains site-wide metadata in FrontMatter
- Defines navigation structure
- Configures pagination

### Template System

#### Layouts
Located in `templates/layouts/`:

1. **`default.html`** - Base layout
   - HTML structure
   - Head section with SEO
   - Header and footer includes
   - `{#insert /}` for content

2. **`post.html`** - Blog post layout
   - Extends default layout
   - Post metadata display
   - Table of contents sidebar
   - Previous/next navigation
   - Tag display

3. **`page.html`** - Simple page layout
   - Extends default layout
   - Clean content area

4. **`tag.html`** - Tag archive layout
   - Lists posts with specific tag

#### Partials
Reusable components in `templates/partials/`:

- **`header.html`** - Site navigation
  - Logo/title
  - Desktop/mobile navigation
  - Theme toggle
  - Social links

- **`posts-paginator.html`** - Pagination controls
  - Page numbers
  - Previous/next buttons
  - Results count

- **`toc.html`** - Table of contents
  - Auto-generated from headings
  - Sticky sidebar on desktop
  - Active section highlighting

#### Custom Tags
Reusable components in `templates/tags/`:

- **`postCard.html`** - Post preview card
  - Date, title, description
  - Tag links
  - Used in post listings

Usage: `{#postCard post site/}`

#### Qute Features Used

**Data Access:**
```html
{site.title}                          <!-- Site title -->
{site.data.navigation}                <!-- FrontMatter data -->
{site.collections.posts}              <!-- Post collection -->
{page.title}                          <!-- Current page title -->
{page.date.format('MM-dd-yyyy')}      <!-- Formatted date -->
```

**Loops:**
```html
{#for post in site.collections.posts.paginated(page.paginator)}
  {#postCard post site/}
{/for}
```

**Conditionals:**
```html
{#if page.data.tags??}
  <!-- Show tags -->
{/if}
```

**Includes:**
```html
{#include partials/header.html /}
```

**Fragments (Reusable sections):**
```html
{#fragment id=menu}
  <!-- Menu content -->
{/fragment}
{#include $menu device='mobile'/}
```

### Frontend Assets

#### JavaScript (`web/app.js`)
Main entry point that:
- Imports font from mvnpm dependency (`@fontsource/atkinson-hyperlegible`)
- Imports highlight.js for code syntax highlighting
- Initializes syntax highlighting

#### Styles (`web/style.css`)
TailwindCSS configuration with:
- Custom theme variables for light/dark modes
- Component classes for site elements
- Utility classes
- Icon definitions using SVG masks

#### Theme Toggle (`web/theme-toggle.js`)
Handles:
- Dark/light mode switching
- LocalStorage persistence
- Icon updates
- Mobile menu toggle

#### Table of Contents (`web/toc.js`)
Provides:
- Active section highlighting
- Smooth scrolling
- Intersection observer for scroll tracking

### Web Bundler Process

The Quarkus Web Bundler automatically:

1. **Scans** `web/` directory for JS/CSS files
2. **Resolves** mvnpm dependencies from `pom.xml`
3. **Bundles** JavaScript using esbuild
4. **Processes** TailwindCSS with PostCSS
5. **Injects** `{#bundle /}` tag with script/style tags
6. **Watches** for changes in dev mode
7. **Optimizes** for production builds

**mvnpm Dependencies** (from `pom.xml`):
- `@fontsource/atkinson-hyperlegible` (5.2.8) - Font
- `highlight.js` (11.11.1) - Code syntax highlighting

## Build System

### Maven Configuration (`pom.xml`)

**Key Dependencies:**

```xml
<!-- Roq Core -->
<dependency>
  <groupId>io.quarkiverse.roq</groupId>
  <artifactId>quarkus-roq</artifactId>
  <version>2.0.3</version>
</dependency>

<!-- Roq Plugins -->
<dependency>
  <groupId>io.quarkiverse.roq</groupId>
  <artifactId>quarkus-roq-plugin-faker</artifactId>
  <version>2.0.3</version>
</dependency>

<dependency>
  <groupId>io.quarkiverse.roq</groupId>
  <artifactId>quarkus-roq-plugin-tagging</artifactId>
  <version>2.0.3</version>
</dependency>

<!-- Web Bundler -->
<dependency>
  <groupId>io.quarkiverse.web-bundler</groupId>
  <artifactId>quarkus-web-bundler</artifactId>
  <version>2.0.4</version>
</dependency>

<dependency>
  <groupId>io.quarkiverse.web-bundler</groupId>
  <artifactId>quarkus-web-bundler-tailwindcss</artifactId>
  <version>2.0.4</version>
</dependency>
```

**Plugins:**
- `quarkus-maven-plugin` - Quarkus build and dev mode
- `maven-compiler-plugin` - Java compilation
- `maven-surefire-plugin` - Testing

### Configuration (`config/application.properties`)

```properties
# Development-only faker data generation
#%dev.quarkus.roq.faker.count.posts=50
```

**Available Configuration Options:**
- `quarkus.roq.faker.count.posts` - Generate fake posts in dev mode
- Standard Quarkus properties (server port, logging, etc.)

## Development Workflow

### Prerequisites

1. **Java 21+** installed
2. **Maven** (or use included Maven wrapper `./mvnw`)
3. **Quarkus CLI** (optional but recommended):
   ```bash
   curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
   curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio
   ```

### Starting Development Mode

**Using Quarkus CLI:**
```bash
quarkus dev
```

**Using Maven:**
```bash
./mvnw quarkus:dev
```

**Features in Dev Mode:**
- Live reload on file changes
- Hot deployment
- Dev UI at `/q/dev`
- Press `w` to open browser automatically
- Press `h` for help

### Creating Content

#### New Blog Post

1. Create file in `content/posts/`:
   ```bash
   # Simple post
   content/posts/2025-01-15-my-post.md
   
   # Post with images
   content/posts/2025-01-15-my-post/
   ├── index.md
   └── image.png
   ```

2. Add FrontMatter:
   ```yaml
   ---
   title: "My Post Title"
   date: 2025-01-15 10:00:00 +0000
   tags: [java, quarkus, roq]
   description: "Brief description"
   toc: true
   ---
   ```

3. Write content in Markdown
4. Save and view at `http://localhost:8080/posts/my-post-title/`

#### New Page

1. Create file in `content/`:
   ```bash
   content/contact.md
   ```

2. Add FrontMatter:
   ```yaml
   ---
   layout: page
   title: "Contact"
   description: "Get in touch"
   ---
   ```

3. Add to navigation in `content/index.html`:
   ```yaml
   navigation:
     - title: Contact
       url: /contact
   ```

### Customizing Design

#### Modify Theme Colors

Edit `web/style.css` in the `@theme` section:

```css
@theme {
    --color-primary: #111827;
    --color-accent-500: #9333ea;
    /* ... more colors */
}
```

Changes apply immediately in dev mode.

#### Add Custom Styles

Add TailwindCSS utilities or custom CSS in `web/style.css`:

```css
@layer components {
    .my-custom-class {
        @apply bg-primary text-white p-4 rounded;
    }
}
```

#### Modify Templates

Edit files in `templates/`:
- `layouts/` - Page structure
- `partials/` - Reusable components
- `tags/` - Custom Qute tags

Changes apply immediately in dev mode.

### Adding JavaScript Functionality

Edit `web/app.js` or create new JS files in `web/`:

```javascript
// web/my-feature.js
export function myFeature() {
    console.log('Feature loaded');
}
```

Import in `web/app.js`:
```javascript
import { myFeature } from './my-feature.js';
myFeature();
```

### Using Roq Plugins

#### Faker Plugin (Development Only)

Generates fake posts for testing pagination and design:

```properties
# config/application.properties
%dev.quarkus.roq.faker.count.posts=50
```

#### Tagging Plugin

Automatically enabled. Provides:
- Tag collection at `/posts/tag/{tag}/`
- Tag listing page
- Tag filtering

## Building for Production

### Generate Static Site

**Using Quarkus CLI:**
```bash
quarkus build -Dquarkus.roq.site.generate=true
```

**Using Maven:**
```bash
./mvnw clean package -Dquarkus.roq.site.generate=true
```

**Output:**
- Static files generated in `target/roq-site/`
- Ready to deploy to any static hosting

### Build Options

**Standard Build (Quarkus app):**
```bash
./mvnw clean package
```
- Creates runnable JAR in `target/`
- Can be deployed as a Quarkus application

**Native Build:**
```bash
./mvnw clean package -Pnative
```
- Creates native executable
- Requires GraalVM

## Deployment

### GitHub Pages (Automated)

The project includes GitHub Actions workflow (`.github/workflows/deploy.yml`):

```yaml
name: Roq Site Deploy

on:
  push:
    branches: [ main ]
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Generate Roq Site
        uses: quarkiverse/quarkus-roq@v1
        with:
          github-token: ${{ secrets.GITHUB_TOKEN }}
  
  deploy:
    environment:
      name: github-pages
      url: ${{ steps.deployment.outputs.page_url }}
    permissions:
      pages: write
      id-token: write
    runs-on: ubuntu-latest
    needs: build
    steps:
      - name: Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
```

**Setup:**
1. Enable GitHub Pages in repository settings
2. Set source to "GitHub Actions"
3. Push to `main` branch
4. Site deploys automatically

### Manual Deployment

1. **Build static site:**
   ```bash
   ./mvnw clean package -Dquarkus.roq.site.generate=true
   ```

2. **Deploy `target/roq-site/` to:**
   - Netlify
   - Vercel
   - AWS S3 + CloudFront
   - Any static hosting service

### Deploy as Quarkus Application

1. **Build JAR:**
   ```bash
   ./mvnw clean package
   ```

2. **Run:**
   ```bash
   java -jar target/quarkus-app/quarkus-run.jar
   ```

3. **Deploy to:**
   - Kubernetes
   - OpenShift
   - Cloud Run
   - Any container platform

## Key Features

### 1. Live Reload
- Changes to content, templates, or assets reload instantly
- No build step required during development
- Browser auto-refreshes

### 2. Type-Safe Templates
- Qute provides compile-time checking
- IDE autocomplete for template expressions
- Catch errors before runtime

### 3. Convention Over Configuration
- File structure determines URLs
- FrontMatter defines metadata
- Minimal configuration needed

### 4. Plugin System
- Extend functionality with Quarkus extensions
- Roq plugins for common features
- Create custom plugins

### 5. Modern Frontend
- TailwindCSS for styling
- Automatic asset bundling
- NPM dependencies via mvnpm
- Dark mode support

### 6. SEO Friendly
- Meta tags from FrontMatter
- Semantic HTML
- Fast page loads
- Static output option

### 7. Developer Experience
- Quarkus Dev Mode
- Hot reload
- Dev UI
- Fast feedback loop

## Common Tasks

### Change Site Title

Edit `content/index.html` FrontMatter:
```yaml
---
title: Your New Title
---
```

### Add Social Links

Edit `content/index.html` FrontMatter:
```yaml
---
social:
  - name: GitHub
    link: https://github.com/username
    icon: github
  - name: Twitter
    link: https://twitter.com/username
    icon: twitter
---
```

Add icon in `web/style.css`:
```css
.icon-twitter { 
  --svg: url("data:image/svg+xml,..."); 
}
```

### Enable Fake Posts

Uncomment in `config/application.properties`:
```properties
%dev.quarkus.roq.faker.count.posts=50
```

### Customize Pagination

Edit `content/index.html` FrontMatter:
```yaml
---
paginate: posts
paginate.size: 10  # Posts per page
---
```

### Add Table of Contents

Add to post FrontMatter:
```yaml
---
toc: true
---
```

### Change Date Format

Edit template files, modify:
```html
{page.date.format('MM-dd-yyyy')}
```

To any Java DateTimeFormatter pattern.

## Troubleshooting

### Port Already in Use

Change port in `config/application.properties`:
```properties
quarkus.http.port=8081
```

### Assets Not Loading

1. Check `web/` directory structure
2. Verify mvnpm dependencies in `pom.xml`
3. Clear `target/` directory: `./mvnw clean`
4. Restart dev mode

### Template Errors

1. Check Qute syntax
2. Verify data availability with `??` operator
3. Check console for error messages
4. Use Dev UI at `/q/dev` for debugging

### Build Failures

1. Ensure Java 21+ is installed
2. Clear Maven cache: `./mvnw clean`
3. Update dependencies: `./mvnw versions:display-dependency-updates`
4. Check for syntax errors in templates

## Resources

- **Roq Documentation:** https://iamroq.com/docs/
- **Quarkus Documentation:** https://quarkus.io/guides/
- **Qute Reference:** https://quarkus.io/guides/qute-reference
- **TailwindCSS Docs:** https://tailwindcss.com/docs
- **Web Bundler:** https://docs.quarkiverse.io/quarkus-web-bundler/

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                         Browser                              │
│  ┌──────────────────────────────────────────────────────┐  │
│  │  HTML + CSS + JS (bundled)                           │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                            ▲
                            │ HTTP
                            │
┌─────────────────────────────────────────────────────────────┐
│                    Quarkus Application                       │
│  ┌──────────────────────────────────────────────────────┐  │
│  │                    Roq Layer                          │  │
│  │  ┌────────────────────────────────────────────────┐  │  │
│  │  │  Content Processing                            │  │  │
│  │  │  - FrontMatter parsing                         │  │  │
│  │  │  - Markdown → HTML                             │  │  │
│  │  │  - Collection management                       │  │  │
│  │  └────────────────────────────────────────────────┘  │  │
│  │  ┌────────────────────────────────────────────────┐  │  │
│  │  │  Qute Template Engine                          │  │  │
│  │  │  - Layout rendering                            │  │  │
│  │  │  - Partial includes                            │  │  │
│  │  │  - Data binding                                │  │  │
│  │  └────────────────────────────────────────────────┘  │  │
│  │  ┌────────────────────────────────────────────────┐  │  │
│  │  │  Plugins                                       │  │  │
│  │  │  - Faker (dev data)                            │  │  │
│  │  │  - Tagging                                     │  │  │
│  │  └────────────────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────────────────┘  │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              Web Bundler                             │  │
│  │  - JavaScript bundling (esbuild)                     │  │
│  │  - TailwindCSS processing                            │  │
│  │  - mvnpm dependency resolution                       │  │
│  └──────────────────────────────────────────────────────┘  │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              Quarkus Core                            │  │
│  │  - HTTP server                                       │  │
│  │  - CDI container                                     │  │
│  │  - Dev mode / Live reload                            │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                            ▲
                            │
┌─────────────────────────────────────────────────────────────┐
│                      Source Files                            │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   content/   │  │  templates/  │  │     web/     │      │
│  │  - *.md      │  │  - layouts/  │  │  - *.js      │      │
│  │  - *.html    │  │  - partials/ │  │  - *.css     │      │
│  │  - posts/    │  │  - tags/     │  │              │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
└─────────────────────────────────────────────────────────────┘
```

## Summary

This Quarkus Roq website combines:
- **Java/Quarkus** for robust backend
- **Roq** for static site generation
- **Qute** for type-safe templating
- **TailwindCSS** for modern styling
- **Web Bundler** for asset management
- **GitHub Actions** for automated deployment

The result is a fast, maintainable, and developer-friendly static site generator that leverages the entire Quarkus ecosystem while providing a smooth content authoring experience.
