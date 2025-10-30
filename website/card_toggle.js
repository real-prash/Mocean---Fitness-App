// Toggle the visibility of card bodies when the card is clicked.
const cards = document.querySelectorAll('.card');
cards.forEach(card => {
    card.addEventListener('click', () => {
        const cardBody = card.querySelector('.card-body');
        const collapsed = cardBody.classList.toggle('collapsed');

        if (collapsed) {
            // Get the height of the content inside the card-body.
            const contentHeight = cardBody.scrollHeight;
            // Set max-height of the collapsed card-body to the height of its content.
            cardBody.style.maxHeight = `${contentHeight}px`;
        } else {
            // If expanding, set max-height back to initial value (0).
            cardBody.style.maxHeight = null;
        }

        // Change the icon based on the collapsed state.
        card.querySelector('.icon').textContent = collapsed ? '-' : '+';
    });
});
