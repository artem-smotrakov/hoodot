package com.gypsyengineer.hoodot;

import com.gypsyengineer.hoodot.core.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.gypsyengineer.hoodot.util.WhatTheHell.whatTheHell;

public final class Viewer {

    private final List<BufferedImage> images;

    public static Viewer viewer(Image... images) {
        return new Viewer(images);
    }

    private Viewer(Image... images) {
        Objects.requireNonNull(images, "hey! images must not be null!");

        if (images.length == 0) {
            throw whatTheHell("at least one image please!");
        }

        this.images = new ArrayList<>();
        for (Image image : images) {
            this.images.add(image.bufferedImage());
        }
    }

    private interface ViewerChangeListener {
        void changed();
    }

    private final class ViewerPanel extends JPanel {

        private int index = 0;
        private List<BufferedImage> images;
        private BufferedImage current;
        private List<ViewerChangeListener> listeners = new ArrayList<>();

        public ViewerPanel(List<BufferedImage> images) {
            this.images = new ArrayList<>(images);
            current = this.images.get(index);
        }

        public int count() {
            return images.size();
        }

        public int current() {
            return index;
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        public boolean hasNext() {
            return index < images.size() - 1;
        }

        private void updateImage(int index) {
            current = images.get(index);
            notifyListeners();
            repaint();

            int x = -(current.getWidth() / 2) + (this.getWidth() / 2);
            int y = -(current.getHeight() / 2) + (this.getHeight() / 2);
            this.setLocation(new Point(x,y));

            this.getParent().doLayout();
        }

        public void showPrevious() {
            if (hasPrevious()) {
                updateImage(--index);
            }
        }

        public void showNext() {
            if (hasNext()) {
                updateImage(++index);
            }
        }

        private void addListener(ViewerChangeListener listener) {
            listeners.add(listener);
        }

        private void notifyListeners() {
            for (ViewerChangeListener listener : listeners) {
                listener.changed();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int x = (this.getWidth() / 2) - (current.getWidth() / 2);
            int y = (this.getHeight() / 2) - (current.getHeight() / 2);
            x = Math.max(0, x);
            y = Math.max(0, y);

            g.drawImage(current, x, y, null);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(current.getWidth(), current.getHeight());
        }
    }

    private final class NavigationPanel extends JPanel implements ViewerChangeListener {

        private final JButton prevButton = new JButton("<");
        private final JButton nextButton = new JButton(">");
        private final JLabel indicator;
        private final ViewerPanel vp;

        public NavigationPanel(final ViewerPanel vp) {
            this.vp = vp;
            this.setLayout(new GridLayout(1, 0));

            indicator = new JLabel();
            indicator.setFont(new Font("Monospaced", Font.PLAIN, 14));
            indicator.setHorizontalAlignment(SwingConstants.CENTER);

            KeyNavigation kn = new KeyNavigation(vp);

            prevButton.addActionListener(e -> vp.showPrevious());
            prevButton.addKeyListener(kn);

            nextButton.addActionListener(e -> vp.showNext());
            nextButton.addKeyListener(kn);

            this.add(prevButton);
            this.add(indicator);
            this.add(nextButton);

            updateButtonStates();
        }

        private void updateButtonStates() {
            prevButton.setEnabled(vp.hasPrevious());
            nextButton.setEnabled(vp.hasNext());

            if (!prevButton.isEnabled() && prevButton.hasFocus()) {
                nextButton.requestFocus();
            }
            if (!nextButton.isEnabled() && nextButton.hasFocus()) {
                prevButton.requestFocus();
            }

            indicator.setText((vp.current() + 1) + " / " + vp.count());
        }

        public void changed() {
            updateButtonStates();
        }
    }

    private final class KeyNavigation extends KeyAdapter {

        private final ViewerPanel panel;

        private KeyNavigation(ViewerPanel panel) {
            this.panel = panel;
        }

        @Override
        public void keyReleased(KeyEvent event) {
            int key = event.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                panel.showPrevious();
            }

            if (key == KeyEvent.VK_RIGHT) {
                panel.showNext();
            }
        }
    }

    public void show() {
        final JFrame f = new JFrame("Image viewer");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BorderLayout());

        final ViewerPanel vp = new ViewerPanel(this.images);
        f.addKeyListener(new KeyNavigation(vp));

        vp.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                f.requestFocus();
            }
        });

        NavigationPanel np = new NavigationPanel(vp);
        vp.addListener(np);

        f.add(new JScrollPane(vp), BorderLayout.CENTER);
        f.add(np, BorderLayout.SOUTH);
        f.pack();

        int frameWidth = f.getWidth();
        int frameHeight = f.getHeight();

        DisplayMode displayMode = f.getGraphicsConfiguration().getDevice().getDisplayMode();
        int screenWidth = displayMode.getWidth();
        int screenHeight = displayMode.getHeight();

        int x = (screenWidth / 2) - (frameWidth / 2);
        int y = (screenHeight / 2) - (frameHeight / 2);
        f.setLocation(x, y);

        f.setVisible(true);
    }

    public void run() {
        SwingUtilities.invokeLater(() -> show());
    }
}
